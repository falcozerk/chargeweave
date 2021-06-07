import React from 'react';
import { InputText } from 'primereact/inputtext';

export const AppTopbar = (props) => {
    return (
        <div className="layout-topbar clearfix">
            <img className="p-link layout-topbar-logo" src="assets/layout/images/chargeweave-logo-white.png" alt="Logo" width="135" />
            <div className="layout-topbar-icons">
                <button type="button" className="p-link">
                    <span className="layout-topbar-item-text">Settings</span>
                    <span className="layout-topbar-icon pi pi-cog" />
                </button>
                <button type="button" className="p-link">
                    <span className="layout-topbar-item-text">User</span>
                    <span className="layout-topbar-icon pi pi-user" />
                </button>
                <button className="p-link layout-menu-button" onClick={props.onToggleMenu}>
                    <span className="pi pi-bars"/>
                </button>
            </div>
        </div>
    );
}
