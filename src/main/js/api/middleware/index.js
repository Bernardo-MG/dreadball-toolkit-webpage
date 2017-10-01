import { CALL_API } from 'api/ActionTypes';

const actionWith = (action) => (data) => {
   const finalAction = Object.assign({}, action, data);

   delete finalAction[CALL_API];

   return finalAction;
};

const appendBase = (url) => {
   let result;

   if (url.indexOf(ROUTE_BASE) === -1) {
      result = ROUTE_BASE + url;
   } else {
      result = url;
   }

   return result;
};

const getParams = (content) => {
   let { params } = content;
   const { page, orderBy, direction } = content;

   if (!params) {
      params = {};
   }

   return {
      ...params,
      page,
      orderBy,
      direction
   };
};

const middleware = (next, action, defaultFetch) => {
   const callAPI = action[CALL_API];

   if (typeof callAPI === 'undefined') {
      return next(action);
   }

   const params = getParams(callAPI);

   let { fetch } = callAPI;
   const { endpoint, requestType, successType, failureType } = callAPI;

   if (!fetch) {
      fetch = defaultFetch;
   }
   if (typeof endpoint !== 'string') {
      throw new Error('Specify a string endpoint URL.');
   }

   const processAction = actionWith(action);

   // Processing request action
   next(processAction({ type: requestType }));

   const url = appendBase(endpoint);

   return fetch(url, params).then(
      (response) => next(processAction({
         type: successType,
         ...response
      })),
      (error) => next(processAction({
         type: failureType,
         error: error.message || 'Request failed'
      }))
   );
};

export default middleware;
