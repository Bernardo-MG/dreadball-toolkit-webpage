import React, { Component } from 'react';

import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';

import * as actions from 'requests/actions/unit';
import { units } from 'models/selectors';

import DbxUnitTable from 'codex/components/DbxUnitTable';

class LoadableDbxUnitTable extends Component {

   componentDidMount() {
      this.props.actions.fetch();
   }

   render() {
      return (
         <DbxUnitTable source={this.props.source}/>
      );
   }
}

const mapStateToProps = (state) => ({
   source: units(state)
});

const mapDispatchToProps = (dispatch) => ({
   actions: bindActionCreators(actions, dispatch)
});

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(LoadableDbxUnitTable);
