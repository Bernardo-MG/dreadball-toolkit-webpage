import React from 'react';
import ReactDOM from 'react-dom';
import { AppBar, Navigation, Link } from 'react-toolbox';
import { Layout, NavDrawer, Panel, Sidebar } from 'react-toolbox';
import { Button } from 'react-toolbox';

import GithubIcon from './icons';

class BaseLayout extends React.Component {
   state = {
         drawerActive: false
     };

   toggleDrawerActive = () => {
       this.setState({ drawerActive: !this.state.drawerActive });
   };
   
   render() {
         return (
             <Layout>
                 <NavDrawer active={ this.state.drawerActive } onOverlayClick={ this.toggleDrawerActive }>
                    {this.props.drawerContent}
                 </NavDrawer>
                 <Panel>
                     <AppBar leftIcon='menu' rightIcon={ this.props.rightIcon } onLeftIconClick={ this.toggleDrawerActive } />
                     <div style={{ flex: 1, overflowY: 'auto', padding: '1.8rem' }}>
                        {this.props.children}
                     </div>
                 </Panel>
             </Layout>
         );
     };
};

class MainDrawerContent extends React.Component {
   render() {
         return (
            <Button label='Players' raised />
         );
     };
};

class MainLayout extends React.Component {
   render() {
         return (
             <BaseLayout drawerContent={ <MainDrawerContent/> } rightIcon={ <GithubIcon/> }>
                {this.props.children}
             </BaseLayout>
         );
     };
};

export default MainLayout;
module.exports = MainLayout;