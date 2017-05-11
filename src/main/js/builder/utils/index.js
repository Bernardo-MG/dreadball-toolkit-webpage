
export const avasToMap = (avas) => {
   var result;
   
   result = avas.map(function(ava) {
      return avaToMap(ava);
   });
   
   return result;
}

const avaToMap = (ava) => {
   var result = ava.affinityGroups.map(function(affinity) {
      return {
         label: affinity,
         affinity: affinity,
         value: affinity,
         rank: false
      }
   });
   
   if(ava.includingRankIncrease){
      result.push({
         label: 'rank_increase',
         affinity: undefined,
         value: 'rank_increase',
         rank: true
      });
   }
   
   return result;
}
