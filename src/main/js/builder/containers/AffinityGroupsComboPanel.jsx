import React, { Component } from 'react';

import PropTypes from 'prop-types';

import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';

import * as actions from 'requests/actions/sponsorAffAva';

import { sponsorAffAvasPaginated } from 'models/selectors';

import { avasToMap } from 'builder/utils';
import SponsorAffinitySelectField from 'builder/components/SponsorAffinitySelectField';

class AffinityGroupsComboPanel extends Component {

   componentDidMount() {
      this.props.actions.fetch();
   }

   render() {
      return (
         <SponsorAffinitySelectField source={this.props.source} />
      );
   }

}

AffinityGroupsComboPanel.propTypes = {
   actions: PropTypes.object.isRequired,
   source: PropTypes.array.isRequired
};

const mapStateToProps = (state) => {
   return {
      source: avasToMap(sponsorAffAvasPaginated(state))
   };
};

const mapDispatchToProps = (dispatch) => {
   return {
      actions: bindActionCreators(actions, dispatch)
   };
};

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(AffinityGroupsComboPanel);
