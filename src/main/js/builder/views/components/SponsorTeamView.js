import React from 'react';

import Box from 'grommet/components/Box';
import Heading from 'grommet/components/Heading';
import Tab from 'grommet/components/Tab';
import Tabs from 'grommet/components/Tabs';

import SponsorAffinityList from 'builder/affinities/containers/SponsorAffinityList';
import SponsorUnitNameList from 'builder/units/containers/SponsorUnitNameList';

import SponsorTeamCost from 'builder/team/components/SponsorTeamCost';

import SponsorAssetsForm from 'builder/team/components/forms/SponsorAssetsForm';
import SponsorNameInput from 'builder/team/containers/input/SponsorNameInput';

import BoundDbxTeamPlayersPanel from 'builder/views/containers/BoundDbxTeamPlayersPanel';

const SponsorTeamView = () =>
   <Tabs>
      <Tab title='assets'>
         <Box justify='center' align='center'>
            <Box>
               <SponsorTeamCost />
            </Box>
            <Box>
               <SponsorNameInput />
            </Box>
            <Heading tag='h2'>assets</Heading>
            <SponsorAssetsForm />
            <Box direction='row' pad='small'>
               <Box pad='small' size='medium'>
                  <Box direction='row'>
                     <Heading tag='h2'>affinities</Heading>
                  </Box>
                  <SponsorAffinityList />
               </Box>
               <Box pad='small' size='medium'>
                  <Heading tag='h2'>players</Heading>
                  <SponsorUnitNameList />
               </Box>
            </Box>
         </Box>
      </Tab>
      <Tab title='players'>
         <Box pad='medium' full={true}>
            <BoundDbxTeamPlayersPanel />
         </Box>
      </Tab>
   </Tabs>;

export default SponsorTeamView;
