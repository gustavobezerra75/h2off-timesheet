import * as api from '../../api/api';

export const JOBS_RECEIVED = 'JOBS_RECEIVED';

export function getTestAction() {
    return (dispatch) => {
        api.getJobs().then((r) => {
            dispatch(jobsReceived(r));
        });
    }
}

export function jobsReceived(jobs) {
    return {
        type: JOBS_RECEIVED,
        jobs: jobs
    }
}