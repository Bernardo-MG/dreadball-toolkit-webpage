import { put, call, select } from 'redux-saga/effects';
import * as types from 'players/actions/actionTypes';
import { fetcherAffinityPlayer as fetcher } from 'players/requests/fetchers';
import { selectCanLoadRatedPlayer as canLoadSelector } from 'players/selectors/request';
import { selectCurrentRatedPlayerPage as currentPageSelector } from 'players/selectors/page';
import { requestSuccess, requestFailure } from 'players/actions/affinityPlayers';
import { selectSponsorAffinities } from 'players/selectors';

function fetch(params) {
   return fetcher.fetch(params);
}

export function* request(action, pageStep) {
   const canLoad = yield select(canLoadSelector);
   if (canLoad) {
      yield put({ type: types.FETCHING_TEAM_PLAYERS });
      const currentPage = yield select(currentPageSelector);
      const page = currentPage + pageStep;
      const affinities = yield select(selectSponsorAffinities);
      const params = { ...action.params, affinities, page };
      let response;
      try {
         response = yield call(fetch, params);
         if (response) {
            yield put(requestSuccess(response.payload, response.pagination));
         } else {
            yield put(requestFailure('Undefined response'));
         }
      } catch (err) {
         yield put(requestFailure(err));
      }
   }
}

export function* requestCurrentPage(action) {
   yield call(request, action, 0);
}

export function* requestNextPage(action) {
   yield call(request, action, 1);
}