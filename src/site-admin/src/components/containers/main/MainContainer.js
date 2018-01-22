import React, {Component} from 'react';
import './MainContainer.css';
import {getJobsAction} from "../../../redux/actions/actionsIndex";
import {connect} from "react-redux";

class MainContainer extends Component {
    componentWillMount() {
        this.props.dispatch(getJobsAction());
    }

    render() {
        console.log(this.props.jobs);
        return (
            <div>
                <h1>Admin</h1>
            </div>
        );
    }
}

export default MainContainer = connect((state) => {
    return {
        jobs: state.data.jobs
    }
})(MainContainer);
