const gulp = require('gulp');
const browserSync = require('browser-sync').create();

gulp.task('default', gulp.series(
    (done) => {
        browserSync.init({
            proxy: "http://localhost:8081",
            port: 8080
        });
        done();
    },
    () => {
        gulp.watch('src/main/resources/templates/**/*.html', (done) => {
            browserSync.reload();
            done();
        });
    }
));