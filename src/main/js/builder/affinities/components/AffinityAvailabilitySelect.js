import React, { Component } from 'react';

import PropTypes from 'prop-types';

import Select from 'grommet/components/Select';

class AffinityAvailabilitySelect extends Component {

   state = {};
   index;
   onChange;

   handleChange = (value) => {
      const selected = value.option;

      this.setState({
         value: selected
      });

      this.onChange(selected.value, this.index);
   };

   componentDidMount() {
      this.props.onChange(this.state.value, this.index);
   }

   constructor(props) {
      super(props);

      this.index = props.index;
      this.onChange = props.onChange;

      if (props.source.length) {
         const first = props.source[0];
         this.state = {
            value: first
         };
      }
   }

   render() {
      return (
         <Select placeHolder='None'
            options={this.props.source}
            value={this.state.value}
            onChange={this.handleChange} />
      );
   }
}

AffinityAvailabilitySelect.propTypes = {
   onChange: PropTypes.func.isRequired,
   index: PropTypes.number.isRequired,
   source: PropTypes.array.isRequired
};

export default AffinityAvailabilitySelect;