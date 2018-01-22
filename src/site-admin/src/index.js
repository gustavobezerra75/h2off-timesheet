import React from 'react';
import ReactDOM from 'react-dom';

import {createStore, applyMiddleware} from 'redux';
import {Provider} from 'react-redux';

import createHistory from 'history/createBrowserHistory';
import {Route} from 'react-router';

import {ConnectedRouter, routerMiddleware} from 'react-router-redux';

import reducers from './redux/reducers/reducersIndex';
import thunk from 'redux-thunk';
import MainContainer from "./components/containers/main/MainContainer";

const history = createHistory();

const middleware = routerMiddleware(history);

const store = createStore(
    reducers,
    applyMiddleware(middleware, thunk)
);


ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <div>
                <Route exact path="/" component={MainContainer}/>
            </div>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);