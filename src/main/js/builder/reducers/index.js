import * as ActionTypes from 'builder/actions/actionTypes';
import { combineReducers } from 'redux';
import assets from 'builder/assets/reducers';
import affinities from 'builder/affinities/reducers';

const sponsor = (
   state = { sponsorName: 'Sponsor name', rank: 0, baseRank: 0, totalCost: 0, players: [], coachingDice: 0, specialMoveCards: 0, nastySurpriseCards: 0, wagers: 0, mediBots: 0, cheerleaders: 0 },
   action) => {
   const { type, payload } = action;

   if (payload === undefined) {
      return { ...state };
   }

   switch (type) {
   case ActionTypes.CLEAR_TEAM:
      return {
         rank: 0,
         baseRank: 0,
         totalCost: 0,
         players: [],
         sponsorName: 'Sponsor name'
      };
   case ActionTypes.ADD_TEAM_PLAYER:
      return {
         ...state,
         players: [...state.players, payload]
      };
   case ActionTypes.REMOVE_TEAM_PLAYER: {
      let playersUpdated = state.players;
      const playerIndex = playersUpdated.indexOf(payload);
      if (playerIndex !== -1) {
         playersUpdated = state.players.filter((x, i) => i !== playerIndex);
      }

      return {
         ...state,
         players: playersUpdated
      };
   }
   case ActionTypes.SET_BASE_RANK:
      return {
         ...state,
         baseRank: payload
      };
   case ActionTypes.SET_RANK:
      return {
         ...state,
         rank: payload
      };
   case ActionTypes.SET_TOTAL_COST:
      return {
         ...state,
         totalCost: payload
      };
   default:
      return state;
   }
};

const dbxBuilderReducer = combineReducers({ sponsor, assets, affinities });

export default dbxBuilderReducer;
