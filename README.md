# prodrem

ProdRem (working name) is a productivity reminder based on git commits.
Currently a work in process, the plan for version 1.0.0 is to notify user by
email when they haven't made a commit to Github in 24 hours.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## TODO

There is still plenty to do, the hope is also to add much more functionality in
the future, including

- Alternative notification methods (texts, calls, push notifications, etc.)
- Allowing setting arbitrary amount of time after commit to be notified
- Defining own notification message(s)
- Having different messages based on time since last commit (i.e. message A
  after 24 hours, message B after 72 hours, etc.)
- Supporting BitBucket, GitLab, Heroku (?), self-served git servers
- More

Check [TODO][] for more.

[todo]: https://github.com/ianhedoesit/prodrem/blob/master/TODO

## Running

To start a web server for the application, run:

    lein ring server

## License

[LICENSE][] Copyright © 2015 Ian Harris

[license]: https://github.com/ianhedoesit/prodrem/blob/master/LICENSE
