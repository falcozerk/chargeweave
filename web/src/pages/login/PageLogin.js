import React, { useState } from 'react';

import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';

import './PageLogin.css';

function PageLogin() {
    const [text, setText] = useState('');

    const onFormSubmit = (e) => {
        if (text) {
            console.log(text);
        }
        setText('');
        e.preventDefault();
    }

    return (
        <div className="pageLogin">
            <h2>Login</h2>

            <form className="p-d-flex p-jc-center p-mt-6" onSubmit={onFormSubmit}>
                <InputText value={text} onChange={(e) => setText(e.target.value)} />
                <Button type="submit" label="Submit" icon="pi pi-check" className="p-ml-2"/>
            </form>

        </div>
    );
}

export default PageLogin;
