import React from 'react';

import PropTypes from 'prop-types';

import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';

import PlayerScrollablePanel from 'builder/players/components/PlayerScrollablePanel';

import { selectRatedPlayers as selectPlayers } from 'models/selectors';
import { selectLastRatedPlayerPage as selectLastPage } from 'models/selectors/page';

import AddIcon from 'grommet/components/icons/base/AddCircle';

import { moveNextPage } from 'models/actions/sponsorPlayer';
import { addTeamPlayer } from 'builder/players/actions';

const SponsorPlayerOptions = (props) =>
   <PlayerScrollablePanel source={props.source} onMore={!props.lastPage ? () => props.nextPage() : null}
      buttonAction={props.buttonAction} buttonIcon={<AddIcon />} />;

SponsorPlayerOptions.propTypes = {
   lastPage: PropTypes.bool.isRequired,
   nextPage: PropTypes.func.isRequired,
   buttonAction: PropTypes.func.isRequired,
   source: PropTypes.array.isRequired
};

const mapStateToProps = (state) => {
   return {
      source: selectPlayers(state),
      lastPage: selectLastPage(state)
   };
};

const mapDispatchToProps = (dispatch) => {
   return {
      nextPage: bindActionCreators(moveNextPage, dispatch),
      buttonAction: bindActionCreators(addTeamPlayer, dispatch)
   };
};

export default connect(
   mapStateToProps,
   mapDispatchToProps
)(SponsorPlayerOptions);