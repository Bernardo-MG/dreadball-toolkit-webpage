// Action key that carries API call info interpreted by this Redux middleware.
export const CALL_API = 'Call API'

// A Redux middleware that interprets actions with CALL_API info specified.
// Performs the call and promises when such actions are dispatched.
export default store => next => action => {
   const callAPI = action[CALL_API]
   if (typeof callAPI === 'undefined') {
      return next(action)
   }
   
   let { endpoint, parse } = callAPI
   const { types } = callAPI
   
   if (typeof endpoint === 'function') {
      endpoint = endpoint(store.getState())
   }
   
   if (typeof parse !== 'function') {
      throw new Error('Specify a json parsing function.')
   }
   if (typeof endpoint !== 'string') {
      throw new Error('Specify a string endpoint URL.')
   }
   if (!Array.isArray(types) || types.length !== 3) {
      throw new Error('Expected an array of three action types.')
   }
   if (!types.every(type => typeof type === 'string')) {
      throw new Error('Expected action types to be strings.')
   }
   
   const actionWith = data => {
      const finalAction = Object.assign({}, action, data)
      delete finalAction[CALL_API]
      return finalAction
   }
   
   const [ requestType, successType, failureType ] = types
   next(actionWith({ type: requestType }))
   
   return callApi(endpoint, parse).then(
      response => next(actionWith({
         type: successType,
         payload: response
      })),
      error => next(actionWith({
         type: failureType,
         error: error.message || 'Something bad happened'
      }))
   )
}

const callApi = (endpoint, parse) => {
   const fullUrl = endpoint
   
   return fetch(fullUrl)
      .then(response =>
         response.json().then(json => {
            if (!response.ok) {
               return Promise.reject(json)
            }
            
            return parse(json)
         })
      )
}