import React, { Component } from 'react';

import PropTypes from 'prop-types';

import { injectIntl } from 'react-intl';

import App from 'grommet/components/App';
import Box from 'grommet/components/Box';
import Button from 'grommet/components/Button';
import Split from 'grommet/components/Split';

import MenuIcon from 'grommet/components/icons/base/Menu';

import MainSidebar from 'components/layout/MainSidebar';

import titleMessages from 'i18n/title';
import appMessages from 'i18n/app';

class BaseLayout extends Component {

   constructor(props) {
      super(props);

      this.state = { navbarVisible: true };
   }

   _onToggleNav() {
      const visible = this.state.navbarVisible;

      this.setState({
         navbarVisible: !visible
      });
   }

   _onResponsiveToggleNav(columns) {
      const visible = columns === 'multiple';

      this.setState({
         navbarVisible: visible
      });
   }

   render() {
      const links = [];
      links.push({ path: '/dbx', label: this.props.intl.formatMessage(titleMessages.dbxTeamBuilder) });
      links.push({ path: '/players', label: this.props.intl.formatMessage(titleMessages.dbxPlayers) });

      const toggle = this._onToggleNav.bind(this);
      const toggleButton = <Button onClick={() => toggle()} icon={<MenuIcon/>} />;
      let headButton;
      if (!this.state.navbarVisible) {
         headButton = toggleButton;
      }
      const menuButton = toggleButton;

      const title = this.props.intl.formatMessage(appMessages.name);

      let nav;
      if (this.state.navbarVisible) {
         nav = <MainSidebar title={title} links={links} menuButton={menuButton} />;
      }

      const toggleResponsive = this._onResponsiveToggleNav.bind(this);
      return (
         <App centered={false}>
            <Split flex="right" separator={true} onResponsive={toggleResponsive}>
               {nav}
               <Box direction='column'>
                  {headButton}
                  {this.props.children}
               </Box>
            </Split>
         </App>
      );
   }
}

BaseLayout.propTypes = {
   children: PropTypes.object.isRequired,
   intl: PropTypes.object.isRequired
};

export default injectIntl(BaseLayout);
