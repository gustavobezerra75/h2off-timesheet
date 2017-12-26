import * as api from '../../api/api';

export const TEST_RECEIVED = 'TEST_RECEIVED';

export function getTestAction() {
    return (dispatch) => {
        api.getTest().then((r) => {
            dispatch(testReceived(r));
        });
    }
}

export function testReceived(test) {
    return {
        type: TEST_RECEIVED,
        test: test
    }
}