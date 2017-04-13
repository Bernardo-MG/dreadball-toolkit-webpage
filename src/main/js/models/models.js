import { Model, many } from 'redux-orm';
import * as types from 'constants/ActionTypes'
import propTypesMixin from 'redux-orm-proptypes';
import { PropTypes } from 'react';

const ValidatingModel = propTypesMixin(Model);

export class Ability extends Model {
   static reducer(state, action, Ability, session) {
      const { type, payload } = action;
      switch (type) {
      case types.CREATE_PLAYERS:
         // Gathers abilities sets
         var abilities = payload.map(player => player.abilities);
         // Merges abilities sets
         abilities = [].concat.apply([], abilities);
         // Creates abilities
         abilities.forEach(ability => Ability.create({name: ability}));
         break;
      }
   }
}
Ability.modelName = 'Ability';
Ability.backend = {
   idAttribute: 'name',
};

export class Player extends ValidatingModel {
   static reducer(state, action, Player, session) {
      const { type, payload } = action;
      switch (type) {
      case types.CREATE_PLAYERS:
         payload.forEach(player => Player.create(player));
         break;
      }
   }
}
Player.modelName = 'Player';
Player.fields = {
    abilities: many('Ability', 'abilities')
};
Player.backend = {
   idAttribute: 'templateName',
};
Player.propTypes = {
    name: PropTypes.string.isRequired,
    role: PropTypes.string.isRequired,
    move: PropTypes.number.isRequired,
    strength: PropTypes.number.isRequired,
    speed: PropTypes.number.isRequired,
    skill: PropTypes.number.isRequired,
    armor: PropTypes.number.isRequired,
    stranger_cost: PropTypes.number,
    ally_cost: PropTypes.number,
    ally_cost: PropTypes.number,
    friend_cost: PropTypes.number,
    cost: PropTypes.number,
    abilities: PropTypes.arrayOf(PropTypes.oneOfType([
        PropTypes.instanceOf(Ability),
        PropTypes.string,
    ])).isRequired,
};
