import React, { Component } from 'react';
import { StyleSheet, View, Image, Text, KeyboardAvoidingView } from 'react-native';
import LoginForm from './LoginForm';

export default class Login extends Component {
    render() {
        return (
            //keyboardAvoidingView behavior="padding" if its needed
            <View behavior="padding" style={styles.container}>
    <View style={styles.logoContainer}>
    <Image
        style={styles.logo}
        source={require('../../images/h2offlogo.png')}
        />

        <Text style={styles.title}> Waterproofing is our Brand</Text>
        </View>
        <View style={styles.formContainer}>
    <LoginForm />
        </View>
        </View>
    );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#3498db'
    },
    logoContainer: {
        alignItems: 'center',
        flexGrow: 1,
        justifyContent: 'center'
    },
    logo: {
        width: 100,
        height: 100
    },
    title: {
        color: '#FFF',
        marginTop: 10,
        width: 160,
        textAlign: 'center',
        opacity: 0.6

    }
});