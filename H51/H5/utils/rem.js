(function () {

    function resize () {
      const html = document.documentElement;
      const width = html.clientWidth;
      html.style.fontSize = `${width / 7.5}px`;
    }
  
    window.addEventListener('resize', resize);
  
    resize();
  })();
  