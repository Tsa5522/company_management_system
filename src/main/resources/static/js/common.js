app.get('/logout', function(req, res){
    req.session.destroy(function(err){
        if(err){
            console.log(err);
        } else {
            res.redirect('/login');
        }
    });
});
const tabs = document.querySelectorAll('[data-tab]');
tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        tabs.forEach(tab => tab.classList.remove('active'));
        tab.classList.add('active');
        localStorage.setItem('activeTab', tab.getAttribute('id'));
    });
});
window.addEventListener('DOMContentLoaded', () => {
    const activeTabId = localStorage.getItem('activeTab');
    if (activeTabId) {
        document.querySelector(`#${activeTabId}`).classList.add('active');
    }
});