import React from 'react';

import PropTypes from 'prop-types';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { setNastySurpriseCard } from 'builder/assets/actions';

import BoundNumberInput from 'components/BoundNumberInput';

import { selectNastySurpriseCards } from 'builder/assets/selectors';

const NastySurpriseCardInput = (props) =>
   <BoundNumberInput {...props}
      handleChange={props.action} />;

NastySurpriseCardInput.propTypes = {
   action: PropTypes.func.isRequired
};

const mapStateToProps = (state) => {
   return {
      value: selectNastySurpriseCards(state)
   };
};

const mapDispatchToProps = (dispatch) => {
   return {
      action: bindActionCreators(setNastySurpriseCard, dispatch)
   };
};

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(NastySurpriseCardInput);
