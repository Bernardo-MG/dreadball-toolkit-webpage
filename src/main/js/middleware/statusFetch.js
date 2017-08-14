import { CALL_API_STATUS } from 'fetch/actions/ActionTypes';
import { fetchStatus } from 'fetch';
import middleware from 'fetch/middleware';

const getParams = (content) => {
   let { params } = content;
   const { orderBy, order } = content;

   if (!params) {
      params = {};
   }

   return {
      ...params,
      orderBy,
      order
   };
};

// A Redux middleware that interprets actions with CALL_API_STATUS info specified.
// Performs the call and promises when such actions are dispatched.
export default () => (next) => (action) => {
   middleware(next, action, getParams, fetchStatus, CALL_API_STATUS);
};