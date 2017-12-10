import React, { Component } from 'react';

import PropTypes from 'prop-types';

import Box from 'grommet/components/Box';

import NextPageButton from 'codex/containers/NextPageButton';
import PreviousPageButton from 'codex/containers/PreviousPageButton';

import DbxUnitList from 'codex/components/DbxUnitList';

class DbxPlayersPanel extends Component {

   units = [];

   constructor(props) {
      super(props);

      this.units = props.units;
   }

   componentWillReceiveProps(props) {
      this.units = props.units;
   }

   render() {
      return (
         <Box pad='medium' full={true}>
            <DbxUnitList source={this.props.units} />
            <Box direction='row' justify='center' align='center'>
               <Box margin='small'>
                  <PreviousPageButton />
               </Box>
               <Box margin='small'>
                  <NextPageButton />
               </Box>
            </Box>
         </Box>
      );
   }
}

DbxPlayersPanel.propTypes = {
   units: PropTypes.array.isRequired
};

export default DbxPlayersPanel;