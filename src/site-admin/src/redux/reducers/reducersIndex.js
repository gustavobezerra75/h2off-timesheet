import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux';
import data from './data';
import {reducer as formReducer} from 'redux-form';

export default combineReducers({
    routing: routerReducer,
    data: data,
    form: formReducer,
});
