import React from 'react';
import SideBar from '../Sidebar/index';
import '../Admin2/Admin2.css';
import sidebar_menu from '../constants/sidebar-menu';


function Layout({ children }) {
    return (
        <div className='dashboard-container'>
            <SideBar menu={sidebar_menu} />
            <div className='dashboard-body'>
                {children}
            </div>
        </div>
    );
}

export default Layout;
