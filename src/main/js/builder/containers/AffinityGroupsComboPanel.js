import React, { Component } from 'react'
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import * as actions from 'requests/actions/sponsorAffAva';
import { sponsorAffAvas } from 'models/selectors';
import { avasToMap } from 'utils';
import ComponentPanel from 'components/ComponentPanel';
import SponsorAffinityComboBox from 'builder/containers/SponsorAffinityComboBox';

class AffinityGroupsComboPanel extends Component {
   
   componentDidMount() {
      this.props.actions.fetch();
   }
   
   render() {
      return (
         <ComponentPanel source={this.props.source} type={SponsorAffinityComboBox} />
      )
   }
}

const mapStateToProps = (state) => ({
   source: avasToMap(sponsorAffAvas(state))
});

const mapDispatchToProps = (dispatch) => ({
   actions: bindActionCreators(actions, dispatch)
});

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(AffinityGroupsComboPanel);
