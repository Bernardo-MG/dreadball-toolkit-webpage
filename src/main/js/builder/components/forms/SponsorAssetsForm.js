import React from 'react';

import Box from 'grommet/components/Box';
import Form from 'grommet/components/Form';
import FormField from 'grommet/components/FormField';

import CheerleadersInput from 'builder/containers/input/CheerleadersInput';
import CoachingDiceInput from 'builder/containers/input/CoachingDiceInput';
import MediBotInput from 'builder/containers/input/MediBotInput';
import NastySurpriseCardInput from 'builder/containers/input/NastySurpriseCardInput';
import SpecialMoveCardInput from 'builder/containers/input/SpecialMoveCardInput';
import WagerInput from 'builder/containers/input/WagerInput';

const SponsorAssetsForm = () =>
   <Form>
      <Box direction='row'>
         <FormField label='coaching_dice'>
            <CoachingDiceInput id='coaching_dice' name='coaching_dice' min={0} max={100}/>
         </FormField>
         <FormField label='special_move_card'>
            <SpecialMoveCardInput id='special_move_card' name='special_move_card' min={0} max={100}/>
         </FormField>
      </Box>
      <Box direction='row'>
         <FormField label='nasty_surprise_card'>
            <NastySurpriseCardInput id='nasty_surprise_card' name='nasty_surprise_card' min={0} max={100}/>
         </FormField>
         <FormField label='wager'>
            <WagerInput id='wager' name='wager' min={0} max={100}/>
         </FormField>
      </Box>
      <Box direction='row'>
         <FormField label='medibot'>
            <MediBotInput id='medibot' name='medibot' min={0} max={100}/>
         </FormField>
         <FormField label='cheerleaders'>
            <CheerleadersInput id='cheerleaders' name='cheerleaders' min={0} max={100}/>
         </FormField>
      </Box>
   </Form>;

export default SponsorAssetsForm;