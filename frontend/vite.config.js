import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/libros': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      '/autores': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
});
