repositories.remote << 'http://www.ibiblio.org/maven2/'

define 'markov' do
    project.version = '0.1.0'
    project.group = 'uk.co.ignignokt'
    package(:jar).with :manifest => { 'Main-Class' => 'uk.co.ignignokt.markov.Main' }
    package :sources
    package :javadoc
    test.with 'commons-collections:commons-collections:jar:3.2'
end
