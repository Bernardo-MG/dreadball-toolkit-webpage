import React, { Component } from 'react';

import Article from 'grommet/components/Article';
import Box from 'grommet/components/Box';
import Button from 'grommet/components/Button';
import EditIcon from 'grommet/components/icons/base/Edit';
import Form from 'grommet/components/Form';
import FormField from 'grommet/components/FormField';
import Header from 'grommet/components/Header';
import Heading from 'grommet/components/Heading';
import Layer from 'grommet/components/Layer';
import NumberInput from 'grommet/components/NumberInput';
import Section from 'grommet/components/Section';
import TextInput from 'grommet/components/TextInput';

import SponsorTeamUnitTable from 'builder/containers/SponsorTeamUnitTable';
import SponsorAddUnitTable from 'builder/containers/SponsorAddUnitTable';
import SponsorAffinityList from 'builder/containers/SponsorAffinityList';
import CheerleadersInput from 'builder/containers/CheerleadersInput';
import CoachingDiceInput from 'builder/containers/CoachingDiceInput';
import MediBotInput from 'builder/containers/MediBotInput';
import NastySurpriseCardInput from 'builder/containers/NastySurpriseCardInput';
import SpecialMoveCardInput from 'builder/containers/SpecialMoveCardInput';
import WagerInput from 'builder/containers/WagerInput';

import SponsorTeamCost from 'builder/components/SponsorTeamCost';

import SponsorAssets from 'builder/components/SponsorAssets';
import SponsorAssetsForm from 'builder/components/forms/SponsorAssetsForm';

class SponsorTeamView extends Component {

   state = { showAssetsForm: false };

   editAssets = () => {
      this.setState({ showAssetsForm: true });
   };

   closeAssetsForm = () => {
      this.setState({ showAssetsForm: false });
   };

   constructor (props) {
     super(props);
   }

   render() {
      let view = null;

      if (this.state.showAssetsForm) {
         view = 
               <Layer>
                  <Heading tag='h2'>assets</Heading>
                  <SponsorAssetsForm />
                  <Button onClick={ this.closeAssetsForm } label='close' />
               </Layer>
      } else {
         view = <div>
            <Article>
            <Header>
               <Heading>sponsor_data</Heading>
            </Header>
            <Section pad="medium">
               <Box direction='row'>
                  <Heading tag='h2'>assets</Heading>
                  <Button onClick={ this.editAssets } icon={ <EditIcon /> } a11yTitle='edit_assets' />
               </Box>
               <SponsorAssets />
            </Section>
         </Article>
         <Form>
            <FormField label='sponsor_name'>
               <TextInput id='sponsor_name' name='sponsor_name'/>
            </FormField>
            <SponsorTeamCost />
            <h1>spend_rank</h1>
            <Button label='additional_affinity' />
            <SponsorAffinityList />
         </Form>
         <SponsorTeamUnitTable />
         <h1>add_player</h1>
         <SponsorAddUnitTable />
      </div>
      }

      return (
         view
      );
   };

};

export default SponsorTeamView;
