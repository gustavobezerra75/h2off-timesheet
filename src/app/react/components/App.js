/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import store from "../redux/store/store";
import {Provider} from 'react-redux';
import Main from "./Main";

export default class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <Main {...this.props} store={store}/>
            </Provider>
        );
    }
}


