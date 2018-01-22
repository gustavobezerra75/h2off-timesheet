import * as Types from '../actions/data';

const initialState = {
    jobs: null
};

export default function dataReducer(state = initialState, action) {
    switch(action.type) {
        case Types.JOBS_RECEIVED:
            return {
                ...state,
                jobs: action.jobs
            };
        default:
            return state;
    }
}