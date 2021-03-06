import { takeLatest, call, select } from 'redux-saga/effects';
import * as types from 'report/actions/actionTypes';
import { fetcherReport as fetcher } from 'report/requests/fetchers';

import { selectAssets } from 'builder/assets/selectors';
import { selectChosenAffinities } from 'builder/affinities/selectors';
import { selectBaseRank } from 'builder/sponsors/selectors';
import { selectPlayers } from 'builder/players/selectors';

export function fetch(params) {
   return fetcher.fetch(params);
}

function* request(action) {
   const assets = yield select(selectAssets);
   const affinities = yield select(selectChosenAffinities);
   const teamPlayers = yield select(selectPlayers);
   const baseRank = yield select(selectBaseRank);

   const params = { ...action.params, affinities, teamPlayers, baseRank, ...assets };
   yield call(fetch, params);
}

export const reportSagas = [
   takeLatest(types.REQUEST_TEAM_REPORT, request)
];
