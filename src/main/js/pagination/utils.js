
export const filterPaginated = (model, idSelector, loader) => (session, pagination) => {
   let ids;
   let result;

   ids = getIds(pagination);   
   if (ids.length) {
      result = filterByIds(model, idSelector, loader, session, ids);
   } else {
      result = [];
   }

   return result;
};

const getIds = (pagination) => {
   let ids;

   if (pagination && pagination.ids) {
      if (pagination.page === undefined){
         ids = pagination.ids;
      } else {
         ids = getSlice(pagination);
      }
   } else {
      ids = [];
   }

   return ids;
};

const getSlice = (pagination) => {
   const start = pagination.page * pagination.numberOfElements;
   const end = start + pagination.numberOfElements;
   const ids = pagination.ids.slice(start, end);

   return ids;
};

const filterByIds = (model, idSelector, loader, session, ids) => {
   let entityLoader;
   let all;

   if (loader){
      entityLoader = loader;
   } else {
      entityLoader = (entity) => entity;
   }

   all = model(session).all().toModelArray();
   return all.filter(entity => ids.includes(idSelector(entity))).map(entity => {
      return entityLoader(entity);
   });
};
