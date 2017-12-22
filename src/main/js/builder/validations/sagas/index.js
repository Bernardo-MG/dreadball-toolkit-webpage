import { put, takeLatest, call, select } from 'redux-saga/effects';
import * as types from 'builder/actions/actionTypes';
import { teamValidationFetcher as fetcher } from 'builder/validations/requests/fetchers';
import { validateTeamSuccess } from 'builder/validations/actions';

import { selectAssets } from 'builder/assets/selectors';
import { selectAffinities } from 'builder/affinities/selectors';
import { selectBaseRank } from 'builder/sponsors/selectors';
import { selectUnits } from 'builder/units/selectors';

function fetch(params) {
   return fetcher.fetch(params);
}

function* requestValidation(action) {
   const response = yield call(fetch, action.params);
   yield put(validateTeamSuccess(response.payload));
}

function* build(action) {
   yield put({ type: types.SET_BASE_RANK, payload: action.payload.baseRank });
   yield put({ type: types.SET_RANK, payload: action.payload.rank });
   yield put({ type: types.SET_TEAM_VALUE, payload: action.payload.teamValue });
}

function* validateTeam() {
   const assets = yield select(selectAssets);
   const affinities = yield select(selectAffinities);
   const units = yield select(selectUnits);
   const baseRank = yield select(selectBaseRank);

   const params = { affinities, units, baseRank, ...assets };
   yield call(requestValidation, { params });
}

export const validationSagas = [
   takeLatest(types.SET_CHEERLEADERS, validateTeam),
   takeLatest(types.SET_COACHING_DICE, validateTeam),
   takeLatest(types.SET_MEDIBOT, validateTeam),
   takeLatest(types.SET_NASTY_SURPRISE_CARD, validateTeam),
   takeLatest(types.SET_SPECIAL_MOVE_CARD, validateTeam),
   takeLatest(types.SET_WAGER, validateTeam),
   takeLatest(types.ADD_TEAM_UNIT, validateTeam),
   takeLatest(types.REMOVE_TEAM_UNIT, validateTeam),
   takeLatest(types.REQUEST_SUCCESS_TEAM_VALIDATION, build)
];