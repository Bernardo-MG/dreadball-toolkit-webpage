import React from 'react';

import PropTypes from 'prop-types';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as Actions from 'builder/actions';

import SponsorBoundNumberInput from 'builder/containers/input/SponsorBoundNumberInput';

const NastySurpriseCardInput = (props) =>
   <SponsorBoundNumberInput {...props}
      handleChange={props.actions.updateSponsorNastySurpriseCard}
      updateSponsor={(value, sponsor) => {
         const result = Object.assign({}, sponsor);
         result.nastySurpriseCards = value;
         return result;
      }} />;

NastySurpriseCardInput.propTypes = {
   actions: PropTypes.object.isRequired
};

const mapStateToProps = (state) => {
   return {
      value: state.builder.sponsor.nastySurpriseCards
   };
};

const mapDispatchToProps = (dispatch) => {
   return {
      actions: bindActionCreators(Actions, dispatch)
   };
};

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(NastySurpriseCardInput);