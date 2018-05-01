import { schema } from 'normalizr';

export const ability = new schema.Entity('abilities', {}, {
   idAttribute: 'name'
});

export const affinity = new schema.Entity('affinities', {}, {
   idAttribute: 'name'
});

export const player = new schema.Entity('players', {
   abilities: [ability],
   affinityGroups: [affinity],
   hatedAffinityGroups: [affinity]
}, {
   idAttribute: 'templateName'
});