
const affinityToMap = (affinity) => {
   return {
      label: affinity,
      affinity,
      value: affinity,
      rank: false
   };
};

const avaToMap = (ava) => {
   const result = ava.affinityGroups.map((affinity) => affinityToMap(affinity));

   if (ava.includingRankIncrease) {
      result.push({
         label: 'rank_increase',
         affinity: undefined,
         value: 'rank_increase',
         rank: true
      });
   }

   return result;
};

export const avasToMap = (avas) => avas.map((ava) => avaToMap(ava));