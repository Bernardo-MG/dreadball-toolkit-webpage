import React from 'react';
import { connect } from 'react-redux';
import Value from 'grommet/components/Value';

const WagerValue = (props) => {
   return (
      <Value value={props.rank} label='wager' />
   );
};

const mapStateToProps = (state) => {
   return {
      rank: state.builder.sponsor.wager
   }
};

const mapDispatchToProps = (dispatch) => ({
});

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(WagerValue);