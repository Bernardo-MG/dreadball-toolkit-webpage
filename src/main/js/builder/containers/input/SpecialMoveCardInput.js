import React from 'react';

import PropTypes from 'prop-types';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { updateSponsorSpecialMoveCard } from 'builder/actions';

import SponsorBoundNumberInput from 'builder/containers/input/SponsorBoundNumberInput';

const SpecialMoveCardInput = (props) =>
   <SponsorBoundNumberInput {...props}
      handleChange={props.action}
      updateSponsor={(value, sponsor) => {
         const result = Object.assign({}, sponsor);
         result.specialMoveCards = value;
         return result;
      }} />;

SpecialMoveCardInput.propTypes = {
   action: PropTypes.func.isRequired
};

const mapStateToProps = (state) => {
   return {
      value: state.builder.sponsor.specialMoveCards
   };
};

const mapDispatchToProps = (dispatch) => {
   return {
      action: bindActionCreators(updateSponsorSpecialMoveCard, dispatch)
   };
};

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(SpecialMoveCardInput);
