(() => {
  const root = document.documentElement;
  const key = 'theme';
  const toggle = document.querySelector('.theme-toggle');

  // Set initial theme from localStorage
  const isDark = (localStorage.getItem(key) || 'light') === 'dark';
  if (isDark) {
    root.classList.add('dark');
  }
  
  if (toggle) {
    toggle.setAttribute('aria-checked', isDark);
  }

  const flip = () => {
    root.classList.toggle('dark');
    const isDarkNow = root.classList.contains('dark');
    localStorage.setItem(key, isDarkNow ? 'dark' : 'light');
    if (toggle) {
      toggle.setAttribute('aria-checked', isDarkNow);
    }
  };

  if (toggle) {
    toggle.addEventListener('click', flip);
    toggle.addEventListener('keydown', e => {
      if (e.key === 'Enter' || e.key === ' ') {
        e.preventDefault();
        flip();
      }
    });
  }
})();
