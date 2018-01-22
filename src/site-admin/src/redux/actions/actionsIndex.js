import {getJobs} from "../../api/apiCalls";

export const JOBS_RECEIVED = "JOBS_RECEIVED";

export function getJobsAction() {
    return (dispatch) => {
       getJobs().then((data) => {
            dispatch(jobsReceived(data));
        }).catch((err) => {
            console.log('error:', err);
        });
    }
}

export function jobsReceived(jobs) {
    return {
        type: JOBS_RECEIVED,
        jobs: jobs
    }
}