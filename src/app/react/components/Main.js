import React, {Component} from 'react';
import {connect} from "react-redux";
import {Text, View, StyleSheet} from "react-native";
import {getTestAction} from "../redux/actions/data";

export class Main extends Component {
    componentWillMount() {
        this.props.dispatch(getTestAction());
    }

    render() {
        console.log(this.props.test);
        return (
            <View style={styles.container}>
                <Text>HELLO</Text>
            </View>
        )
    }
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});

export default Main = connect((state) => {
    return {
        test: state.data.test
    }
})(Main);