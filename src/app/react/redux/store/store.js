/**

 ▬▬ι═══════ﺤ            -═══════ι▬▬
 Created by Chris on 24/08/17.
 ▬▬ι═══════ﺤ            -═══════ι▬▬

 **/

import {applyMiddleware, createStore} from "redux";
import thunk from "redux-thunk";
import main from "../reducers/main";

export default createStore(
    main,
    applyMiddleware(thunk)
);