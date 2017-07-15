import React, { Component } from 'react';

import Article from 'grommet/components/Article';
import Box from 'grommet/components/Box';
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

import SponsorTeamEditionLayer from 'builder/components/layers/SponsorTeamEditionLayer';

import SponsorNameLabel from 'builder/containers/labels/SponsorNameLabel';

import EditionButton from 'components/buttons/EditionButton';

class SponsorTeamView extends Component {

   state = {
            editingAssets: false,
            editingPlayers: false,
            editingName: false
         };

   editAssets = () => {
      this.setState({ ...this.state, editingAssets: true });
   };

   finishEditAssets = () => {
      this.setState({ ...this.state, editingAssets: false });
   };

   editPlayers = () => {
      this.setState({ ...this.state, editingPlayers: true });
   };

   finishEditPlayers = () => {
      this.setState({ ...this.state, editingPlayers: false });
   };

   editName = () => {
      this.setState({ ...this.state, editingName: true });
   };

   finishEditName = () => {
      this.setState({ ...this.state, editingName: false });
   };

   constructor (props) {
     super(props);
   }

   render() {
      let view = null;

      if (this.state.editingAssets) {
         view = 
               <SponsorTeamEditionLayer title='assets' onClose={ this.finishEditAssets }>
                  <SponsorAssetsForm />
               </SponsorTeamEditionLayer>
      } else if (this.state.editingPlayers) {
         view = 
            <SponsorTeamEditionLayer title='players' onClose={ this.finishEditPlayers }>
               <SponsorAddUnitTable />
            </SponsorTeamEditionLayer>
      } else if (this.state.editingName) {
         view = 
            <SponsorTeamEditionLayer title='players' onClose={ this.finishEditName }>
               <Form>
                  <FormField label='sponsor_name'>
                     <TextInput id='sponsor_name' name='sponsor_name'/>
                  </FormField>
               </Form>
            </SponsorTeamEditionLayer>
      } else {
         view = 
            <div>
               <Article>
                  <Header>
                     <Heading>sponsor_data</Heading>
                  </Header>
                  <Section pad="medium">
                     <Box direction='row'>
                        <SponsorNameLabel/>
                        <EditionButton onClick={ this.editName } a11yTitle='edit_name' />
                     </Box>
                  </Section>
                  <SponsorTeamCost />
                  <Section pad="medium">
                     <Box direction='row'>
                        <Heading tag='h2'>assets</Heading>
                        <EditionButton onClick={ this.editAssets } a11yTitle='edit_assets' />
                     </Box>
                     <SponsorAssets />
                  </Section>
                  <Section pad="medium">
                     <Box direction='row'>
                        <Heading tag='h2'>affinities</Heading>
                        <EditionButton onClick={ this.editAffinities } a11yTitle='edit_affinities' />
                     </Box>
                     <SponsorAffinityList />
                  </Section>
                  <Section pad="medium">
                     <Box direction='row'>
                        <Heading tag='h2'>players</Heading>
                        <EditionButton onClick={ this.editPlayers } a11yTitle='edit_players' />
                     </Box>
                     <SponsorTeamUnitTable />
                  </Section>
               </Article>
            </div>
      }

      return (
         view
      );
   };

};

export default SponsorTeamView;
