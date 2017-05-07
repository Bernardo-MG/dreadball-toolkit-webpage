import { Model, many, attr } from 'redux-orm';
import { REQUEST_UNITS_SUCCESS } from 'requests/actions/ActionTypes'
import propTypesMixin from 'redux-orm-proptypes';
import { PropTypes } from 'react';
import { forEachValue } from 'utils';

const ValidatingModel = propTypesMixin(Model);

export class Ability extends Model {
   static reducer(action, Ability, session) {
      const { type, payload } = action;
      switch (type) {
      case REQUEST_UNITS_SUCCESS:
         // Gathers abilities sets
         const abilities = payload.entities.abilities;
         // Creates abilities
         forEachValue(abilities,
               ability => {
                  if (!Ability.filter({ name: ability.name }).exists()) {
                     Ability.create(ability)
                  }
               }
         );
         break;
      }
   }
}
Ability.modelName = 'Ability';
Ability.options = {
   idAttribute: 'name',
};

export class Player extends ValidatingModel {
   static reducer(action, Player, session) {
      const { type, payload } = action;
      switch (type) {
      case REQUEST_UNITS_SUCCESS:
         const units = payload.entities.units;
         forEachValue(units,
               unit => {
                  if (!Player.filter({ templateName : unit.templateName }).exists()) {
                     Player.create(unit)
                  }
               }
         );
         break;
      }
   }
}
Player.modelName = 'Player';
Player.fields = {
   name: attr(),
   role: attr(),
   move: attr(),
   strength: attr(),
   speed: attr(),
   skill: attr(),
   armor: attr(),
   stranger_cost: attr(),
   ally_cost: attr(),
   ally_cost: attr(),
   friend_cost: attr(),
   cost: attr(),
   abilities: many('Ability', 'abilities')
};
Player.options = {
   idAttribute: 'templateName',
};
//Player.propTypes = {
//   name: PropTypes.string.isRequired,
//   role: PropTypes.string.isRequired,
//   move: PropTypes.number.isRequired,
//   strength: PropTypes.number.isRequired,
//   speed: PropTypes.number.isRequired,
//   skill: PropTypes.number.isRequired,
//   armor: PropTypes.number.isRequired,
//   stranger_cost: PropTypes.number,
//   ally_cost: PropTypes.number,
//   ally_cost: PropTypes.number,
//   friend_cost: PropTypes.number,
//   cost: PropTypes.number,
//   abilities: PropTypes.arrayOf(PropTypes.oneOfType([
//      PropTypes.instanceOf(Ability),
//      PropTypes.string,
//   ])).isRequired,
//};
