import { createSelector } from 'reselect';
import { createSelector as ormCreateSelector } from 'redux-orm';
import orm from 'models';
import { filterById } from 'models/selectors/filter';
import { loadPlayer, loadSponsorAffAva } from 'models/selectors/loader';

// Selects the state managed by Redux-ORM.
const ormSelector = (state) => {
   return state.orm;
};

const templateNameSelector = (entity) => {
   return entity.templateName;
};

const nameSelector = (entity) => {
   return entity.name;
};

export const unitsSponsor = createSelector(
   ormSelector,
   state => state.builder.sponsor.units,
   ormCreateSelector(orm, filterById(templateNameSelector, loadPlayer, (session) => session.RatedPlayer))
);

export const unitsPaginated = createSelector(
   ormSelector,
   state => state.pagination.units,
   ormCreateSelector(orm, filterById(templateNameSelector, loadPlayer, (session) => session.Player))
);

export const ratedUnitsPaginated = createSelector(
   ormSelector,
   state => state.pagination.ratedUnits,
   ormCreateSelector(orm, filterById(templateNameSelector, loadPlayer, (session) => session.RatedPlayer))
);

export const sponsorAffAvasPaginated = createSelector(
   ormSelector,
   state => state.pagination.sponsorAffAvas,
   ormCreateSelector(orm, filterById(nameSelector, loadSponsorAffAva, (session) => session.SponsorAffinityAvailability))
);
