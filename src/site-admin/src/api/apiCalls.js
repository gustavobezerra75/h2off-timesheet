import * as util from "./util";

export function getJobs(params) {
    return util.get("/jobs", null, params).then((response) => {
        return response;
    }).catch((e) => e);
}