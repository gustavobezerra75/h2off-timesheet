import * as type from '../actions/actionsIndex';

let initialState = {
    jobs: null
};

export default (state = initialState, action) => {
    switch(action.type) {
        case type.JOBS_RECEIVED:
            return {
                ...state,
                jobs: action.jobs
            }
        default:
            return state;
    }
}