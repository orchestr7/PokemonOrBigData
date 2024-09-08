config.devServer = Object.assign(
    {},
    config.devServer || {},
    {
        port: 8080,
        historyApiFallback: true,
        proxy: [
                {
                    context: ["/api/**"],
                    target: 'http://localhost:8081',
                    logLevel: 'debug',
                },
        ]
    }
);
