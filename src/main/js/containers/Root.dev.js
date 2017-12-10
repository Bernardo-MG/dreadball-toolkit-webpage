import React from 'react';

import PropTypes from 'prop-types';

import { Provider } from 'react-redux';
import routes from 'routes';
import { Router } from 'react-router';
import { IntlProvider } from 'react-intl';
import Cookie from 'js-cookie';
import DevTools from 'components/DevTools';

const locale = Cookie.get('locale') || 'en';

const Root = ({ store, history }) => (
   <IntlProvider locale={locale}>
      <Provider store={store}>
         <div>
            <Router history={history} routes={routes} />
            <DevTools />
         </div>
      </Provider>
   </IntlProvider>
);

Root.propTypes = {
   store: PropTypes.object.isRequired,
   history: PropTypes.object.isRequired
};

export default Root;