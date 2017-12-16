import * as types from 'builder/actions/ActionTypes';

export const clearTeam = () => {
   return {
      type: types.CLEAR_TEAM
   };
};

export const addTeamUnit = (unit) => {
   return {
      type: types.ADD_TEAM_UNIT,
      payload: unit
   };
};

export const removeTeamUnit = (unit) => {
   return {
      type: types.REMOVE_TEAM_UNIT,
      payload: unit
   };
};

export const validateSponsorTeam = (
   affinities = [],
   units = [],
   baseRank = 0,
   cheerleaders = 0, coachingDice = 0, mediBots = 0, specialMoveCards = 0, nastySurpriseCards = 0, wagers = 0) => {
   return {
      type: types.TEAM_VALIDATION,
      params: { affinities, units, baseRank, cheerleaders, coachingDice, mediBots, specialMoveCards, nastySurpriseCards, wagers }
   };
};

export const validateSponsorTeamSuccess = (payload) => {
   return {
      type: types.REQUEST_SUCCESS_SPONSOR_TEAM_VALIDATION,
      payload
   };
};

export const updateSponsorName = (value) => {
   return {
      type: types.SET_SPONSOR_NAME,
      payload: value
   };
};

export const updateSponsorCoachingDice = (value) => {
   return {
      type: types.SET_SPONSOR_COACHING_DICE,
      payload: value
   };
};

export const updateSponsorSpecialMoveCard = (value) => {
   return {
      type: types.SET_SPONSOR_SPECIAL_MOVE_CARD,
      payload: value
   };
};

export const updateSponsorNastySurpriseCard = (value) => {
   return {
      type: types.SET_SPONSOR_NASTY_SURPRISE_CARD,
      payload: value
   };
};

export const updateSponsorWager = (value) => {
   return {
      type: types.SET_SPONSOR_WAGER,
      payload: value
   };
};

export const updateSponsorMediBot = (value) => {
   return {
      type: types.SET_SPONSOR_MEDIBOT,
      payload: value
   };
};

export const updateSponsorCheerleaders = (value) => {
   return {
      type: types.SET_SPONSOR_CHEERLEADERS,
      payload: value
   };
};