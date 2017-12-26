import * as Types from '../actions/data';

const initialState = {
    test: null
};

export default function dataReducer(state = initialState, action) {
    switch(action.type) {
        case Types.TEST_RECEIVED:
            return {
                ...state,
                test: action.test
            };
        default:
            return state;
    }
}